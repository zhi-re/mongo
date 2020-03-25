package re.zhi.config;

import org.ansj.dic.PathToStream;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @description 解析classpath下的字典
 */
@Component
public class Classpath2Stream extends PathToStream {
    @Override
    public InputStream toStream(String path) {
        String libPath = path.substring(8).split("\\|")[1];
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(libPath);
        return stream;
    }
}
