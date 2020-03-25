package re.zhi.config;

import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 分词器配置类
 */
@Configuration
public class AnsjAnalysisConfiguration {
    @Bean
    public IndexAnalysis indexAnalysis() {
        return new IndexAnalysis();
    }

    @Bean
    public ToAnalysis toAnalysis() {
        return new ToAnalysis();
    }
}
