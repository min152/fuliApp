package project.first.lal;

import android.first.lal.R;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
//    private ProgressWheel mProgressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_foot_view);
//        mWebView = (WebView) findViewById(R.id.web_view);
//        mWebView.getSettings().setJavaScriptEnabled(true);
////        mWebView.loadUrl("http://www.zhainanfulishe.net/2872.html");
//        String data = "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >" +
//                "<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\n" +
//                "<p>发行时间：2014/01/07</p>\n" +
//                "<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>" +
//                "<img src=\"http://www.apppn.com/i/v/MXGS-933.jpg\" alt=\"MXGS-933\" >\" +\n" +
//                "                \"<p>作品名：アナルを犯され続けた若妻の悲劇 夫不在の1週間 上原亜衣</p>\\n\" +\n" +
//                "                \"<p>发行时间：2014/01/07</p>\\n\" +\n" +
//                "                \"<p>番号：RBD-549</p>";
//        mWebView.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

}
