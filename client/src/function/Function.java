package function;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.text.Normalizer;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;

/**
 * @author ThienDepZaii - SystemError
 * @Facebook /ThienDz.SystemError
 * @Gmail ThienDz.DEV@gmail.com
 */
public class Function {

    public static void exit() {
        System.exit(0);
    }

    public static void alert(String content) {
        JOptionPane.showMessageDialog(null, content);
    }

    public static long getCurrentMilis() {
        return new Date().getTime();
    }

    public static String time(int second) {
        String result = "";
        int numberOfMinutes;
        int numberOfSeconds;
        numberOfMinutes = ((second % 86400) % 3600) / 60;
        numberOfSeconds = ((second % 86400) % 3600) % 60;
        if (numberOfMinutes > 0) {
            result += numberOfMinutes + " minute ";
        }
        if (numberOfSeconds > 0) {
            result += numberOfSeconds + " second";
        }
        return result;
    }

    public static int getInt(String content) {
        String regex = "([0-9]+?)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        String res = "";
        while (m.find()) {
            res += m.group();
        }
        try {
            return Integer.parseInt(res);
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    public static double roundReal(double d, int index) {
        int j = 1;
        for (int i = 0; i < index; i++) {
            j *= 10;
        }
        return (double) Math.floor(d * j) / j;
    }

    public static String URLEncoder(String url) {
        String encoder = null;
        try {
            encoder = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return encoder;
    }

    //parse value type text để send request
    public static String convertVIToEN(String str) {
        try {
            String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").toLowerCase().replaceAll(" ", "-").replaceAll("đ", "d");
        } catch (Exception ex) {
        }
        return "";
    }

    public static void debug(String content) {
        System.out.println(content);
    }

    public static boolean fixHTTPS() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            return true;
        } catch (GeneralSecurityException e) {
            System.out.println("FixHTTPSError => " + e.toString());
            return false;
        }
    }

    public static void openTabBrowser(String url) {
        String path[] = new String[]{
            "C:\\Users\\" + Function.getUserName() + "\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe",
            "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe",
            "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
            "C:\\Program Files\\Internet Explorer\\iexplore.exe"
        };
        for (String s : path) {
            try {
                Function.shell(s, url, "--new-tab", "--full-screen");
                return;
            } catch (IOException e) {
            }
        }
    }

    public static void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ex) {
        }
    }

    public static void shell(String... shell) throws IOException {
        if (getOSName().toLowerCase().startsWith("windows")) {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(shell);
            builder.directory(new File(System.getProperty("user.home")));
            builder.start();
        }
    }

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getScriptDir() {
        return System.getProperty("user.dir");
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }
}