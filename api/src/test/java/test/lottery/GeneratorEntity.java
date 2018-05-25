package test.lottery;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

/**
 * Created by Sunron on 2017/2/22.
 */
public class GeneratorEntity {

    public static DataSource getDataSource() {
        PropKit.use("config.properties");

        String jdbcUrl = PropKit.get("jdbc.url").trim();
        String jdbcUser = PropKit.get("jdbc.user").trim();
        String jdbcPassword = PropKit.get("jdbc.password").trim();

        // 配置Druid数据库连接池插件
        DruidPlugin druidPlugin = new DruidPlugin(jdbcUrl, jdbcUser, jdbcPassword);
        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "com.lottery.agent.entity";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "/entity";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.lottery.agent.dao";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/dao";

        // 创建生成器
        Generator generator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
//        // 设置是否生成链式 setter 方法
//        generator.setGenerateChainSetter(false);
//        // 添加不需要生成的表名
//        generator.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成链式 setter 方法
        generator.setGenerateChainSetter(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        generator.setRemovedTableNamePrefixes("t_");
        // 生成（使用时打开注释）
        generator.generate();
    }
}
