package smartsearch;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchFrame extends JFrame {

    JList<String> listWeb;
    JTextField tf1, tf2, tf3;
    JTextArea txtResult;

    public SearchFrame() {

        setTitle("Tìm kiếm thông tin trên Internet");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ======================================
        // DANH SÁCH WEB
        // ======================================
        JLabel lblWeb = new JLabel("Danh sách web");
        lblWeb.setBounds(40, 30, 200, 30);
        add(lblWeb);

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("https://dantri.com.vn");
        model.addElement("https://vnexpress.net");
        model.addElement("https://24h.com.vn");
        model.addElement("https://zingnews.vn");
        model.addElement("https://www.thegioididong.com");
        model.addElement("https://dienmayxanh.com");
        model.addElement("https://fptshop.com.vn");

        listWeb = new JList<>(model);

        JScrollPane spWeb = new JScrollPane(listWeb);
        spWeb.setBounds(40, 70, 350, 500);
        add(spWeb);

        // ======================================
        // TỪ KHÓA
        // ======================================
        JLabel l1 = new JLabel("Từ khóa 1");
        l1.setBounds(420, 100, 200, 30);
        add(l1);

        tf1 = new JTextField();
        tf1.setBounds(420, 130, 300, 30);
        add(tf1);

        JLabel l2 = new JLabel("Từ khóa 2");
        l2.setBounds(420, 170, 200, 30);
        add(l2);

        tf2 = new JTextField();
        tf2.setBounds(420, 200, 300, 30);
        add(tf2);

        JLabel l3 = new JLabel("Từ khóa 3");
        l3.setBounds(420, 240, 200, 30);
        add(l3);

        tf3 = new JTextField();
        tf3.setBounds(420, 270, 300, 30);
        add(tf3);

        // ======================================
        // NÚT TÌM KIẾM NỘI DUNG
        // ======================================
        JButton btnSearch = new JButton("Tìm kiếm nội dung");
        btnSearch.setBounds(420, 330, 300, 50);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 18));
        add(btnSearch);

        // ======================================
        // NÚT TÌM GIÁ SẢN PHẨM
        // ======================================
        JButton btnPrice = new JButton("Tìm giá sản phẩm");
        btnPrice.setBounds(420, 400, 300, 50);
        btnPrice.setBackground(new Color(200, 255, 200));
        btnPrice.setFont(new Font("Arial", Font.BOLD, 18));
        add(btnPrice);

        // ======================================
        // KẾT QUẢ
        // ======================================
        JLabel lblKQ = new JLabel("Kết quả");
        lblKQ.setBounds(760, 30, 200, 30);
        add(lblKQ);

        txtResult = new JTextArea();
        txtResult.setEditable(false);
        txtResult.setLineWrap(true);
        txtResult.setWrapStyleWord(true);

        JScrollPane spKQ = new JScrollPane(txtResult);
        spKQ.setBounds(760, 70, 380, 500);
        add(spKQ);

        btnSearch.addActionListener(e -> searchNews());
        btnPrice.addActionListener(e -> searchPrice());
    }

    // ======================================================
    // TẢI HTML
    // ======================================================
    private String downloadHTML(String link) {
    StringBuilder sb = new StringBuilder();
    try {
        URL url = new URL(link);
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        // *** ĐÃ SỬA ĐỔI: Thêm User-Agent để giả lập trình duyệt ***
        c.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        // *** ĐÃ SỬA ĐỔI: Tăng thời gian chờ kết nối lên 10 giây ***
        c.setConnectTimeout(10000); 
        
        // *** ĐÃ SỬA ĐỔI: Kiểm tra mã phản hồi HTTP ***
        int responseCode = c.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.err.println("Lỗi HTTP Code: " + responseCode + " khi truy cập " + link);
            return "";
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(), "UTF-8"));
        String line;

        while ((line = br.readLine()) != null)
            sb.append(line).append("\n");

        br.close();

    } catch (Exception ex) {
        System.err.println("Lỗi tải HTML: " + ex.getMessage());
        return "";
    }
    return sb.toString();
}

    // ======================================================
    // BỎ HTML GIỮ LẠI VĂN BẢN
    // ======================================================
    // ======================================================
// BỎ HTML GIỮ LẠI VĂN BẢN (ĐÃ SỬA LỖI)
// ======================================================
// ======================================================
// BỎ HTML GIỮ LẠI VĂN BẢN (ĐÃ SỬA LỖI MẠNH MẼ HƠN)
// ======================================================
// ======================================================
// BỎ HTML GIỮ LẠI VĂN BẢN (ĐÃ SỬA LỖI LỌC MẠNH MẼ VÀ ĐA DẠNG)
// ======================================================
private String stripHTML(String html) {
    // 1. Tạm thời giữ lại dấu chấm câu/ranh giới câu để hỗ trợ việc tách câu sau này
    // Thay thế các thẻ kết thúc phổ biến bằng dấu chấm và khoảng trắng để tách câu
    String textWithMarkers = html.replaceAll("</p>", ". ").replaceAll("</h1>", ". ").replaceAll("</h2>", ". ").replaceAll("</div>", ". ");
    
    // 2. Xóa các thuộc tính và thẻ liên quan đến hình ảnh/định dạng (nguồn gốc của các chuỗi lỗi)
    
    // Loại bỏ các thẻ HTML chứa nội dung rác (ví dụ: các thẻ script, style)
    textWithMarkers = textWithMarkers.replaceAll("(?s)<script.*?/script>", " ");
    textWithMarkers = textWithMarkers.replaceAll("(?s)<style.*?/style>", " ");
    
    // Loại bỏ các thuộc tính HTML gây lỗi (data-, class=, style=)
    textWithMarkers = textWithMarkers.replaceAll("data-[^\\s>]+=\\\"[^\\\"]*\\\"", " ");
    textWithMarkers = textWithMarkers.replaceAll("class=\\\"[^\\\"]*\\\"", " ");
    textWithMarkers = textWithMarkers.replaceAll("style=\\\"[^\\\"]*\\\"", " ");
    
    // Xóa các đường link và tham chiếu URL
    textWithMarkers = textWithMarkers.replaceAll("href=\\\"[^\\\"]+\\\"", " "); 
    textWithMarkers = textWithMarkers.replaceAll("src=\\\"[^\\\"]+\\\"", " "); 
    
    // 3. Loại bỏ tất cả thẻ HTML còn lại
    String strippedText = textWithMarkers.replaceAll("<[^>]*>", " ");
    
    // 4. Lọc các chuỗi rác còn sót lại (những chuỗi bạn thấy trong kết quả)
    strippedText = strippedText.replaceAll("cdnphoto", " ");
    strippedText = strippedText.replaceAll("highlight", " ");
    strippedText = strippedText.replaceAll("zoom[0-9]{3}", " ");
    strippedText = strippedText.replaceAll("specialcss", " ");
    strippedText = strippedText.replaceAll("csfpg", " ");
    strippedText = strippedText.replaceAll("vn|vne\\s+", " ");
    strippedText = strippedText.replaceAll("article:body_text", " ");
    strippedText = strippedText.replaceAll("sub\\s*highlight", " ");
    strippedText = strippedText.replaceAll("eventsrc", " ");

    // 5. Chuẩn hóa khoảng trắng và cắt đầu cuối
    strippedText = strippedText.replaceAll("[\\n\\t\\r]", " ");
    strippedText = strippedText.replaceAll("\\s+", " ").trim();
    
    return strippedText;
}
    // ======================================================
// TÌM KIẾM NỘI DUNG (ĐÃ SỬA LỖI)
// ======================================================
// ======================================================
// TÌM KIẾM NỘI DUNG (ĐÃ SỬA LỖI VÀ TÁCH CÂU CHÍNH XÁC)
// ======================================================
private void searchNews() {

    txtResult.setText("");

    String k1 = tf1.getText().trim().toLowerCase();
    String k2 = tf2.getText().trim().toLowerCase();
    String k3 = tf3.getText().trim().toLowerCase();
    
    boolean hasKeyword = k1.length() > 0 || k2.length() > 0 || k3.length() > 0;

    if (!hasKeyword) {
        txtResult.append("❗ Vui lòng nhập ít nhất một từ khóa vào ô Từ khóa 1.");
        return;
    }

    for (String web : listWeb.getSelectedValuesList()) {

        txtResult.append("\n============================\n");
        txtResult.append("🌐 Web: " + web + "\n");
        txtResult.append("============================\n");

        String html = downloadHTML(web);

        if (html.isEmpty()) {
            txtResult.append("⚠ Không tải được trang hoặc Lỗi kết nối.\n");
            continue;
        }

        String text = stripHTML(html).toLowerCase();
        
        // Chia văn bản thành các câu dựa trên dấu chấm, hỏi, than
        String[] sentences = text.split("(?<=[.?!])\\s+");

        boolean found = false;
        int count = 0; // Giới hạn số câu tìm được

        for (String s : sentences) {
            String sentence = s.trim(); 
            
            // Bỏ qua các câu quá ngắn hoặc chỉ chứa ký hiệu rác
            if (sentence.length() < 20 || sentence.contains("http") || sentence.contains("cdnphoto")) {
                continue;
            }

            // Logic tìm kiếm: Kiểm tra nếu câu chứa k1 HOẶC k2 HOẶC k3
            if ((k1.length() > 0 && sentence.contains(k1))
            ||  (k2.length() > 0 && sentence.contains(k2))
            ||  (k3.length() > 0 && sentence.contains(k3))) {

                // Đảm bảo câu kết thúc bằng dấu chấm câu (nếu chưa có)
                if (!sentence.matches(".*[.?!]$")) {
                    sentence += "."; 
                }
                
                txtResult.append("• " + sentence.substring(0, Math.min(sentence.length(), 400)) + "\n\n"); // Giới hạn độ dài câu
                found = true;
                count++;
                
                if (count >= 5) break; // Chỉ hiển thị tối đa 5 câu tìm được cho mỗi web
            }
        }

        if (!found) {
            txtResult.append("❌ Không tìm thấy nội dung liên quan.\n\n");
        }
    }
}

    // ======================================================
    // TÌM GIÁ SẢN PHẨM (HOÀN THIỆN)
    // ======================================================
    private void searchPrice() {

        String name = tf1.getText().trim();

        if (name.isEmpty()) {
            txtResult.setText("❗ Nhập tên sản phẩm vào ô Từ khóa 1.");
            return;
        }

        String keyword = name.replace(" ", "+");

        txtResult.setText("🔍 Đang tìm giá sản phẩm: " + name + "\n\n");

        // TMĐT
        txtResult.append(checkPrice("Shopee", "https://shopee.vn/search?keyword=" + keyword));
        txtResult.append(checkPrice("Tiki", "https://tiki.vn/search?q=" + keyword));
        txtResult.append(checkPrice("Lazada", "https://www.lazada.vn/catalog/?q=" + keyword));

        // ⭐ TRANG BẠN YÊU CẦU
        txtResult.append(searchTGDD(keyword));
        txtResult.append(searchDMX(keyword));
        txtResult.append(searchFPT(keyword));
    }

    // ======================================================
// TÌM GIÁ CHO SÀN TMĐT (ĐÃ SỬA LỖI)
// ======================================================
private String checkPrice(String site, String url) {

    String html = downloadHTML(url);
    
    if (html.isEmpty())
        return site + ": ⚠ Không truy cập được\n\n";

    // *** ĐÃ SỬA ĐỔI: Sử dụng Regex để tìm kiếm số tiền trước ký hiệu ₫ ***
    // Regex: (\d[\d.,]*) tìm kiếm một chuỗi số, có thể bao gồm dấu chấm/phẩy, trước ₫
    Pattern pattern = Pattern.compile("(\\d[\\d.,]*)\\s*₫");
    Matcher matcher = pattern.matcher(html);

    if (matcher.find()) {
        String price = matcher.group(1); 
        return site + ": 💰 " + price + "₫\n\n";
    }

    return site + ": ❌ Không tìm thấy giá (hoặc sản phẩm hết hàng).\n\n";
}
    // ======================================================
    // ⭐ THẾ GIỚI DI ĐỘNG
    // ======================================================
   // ======================================================
// ⭐ THẾ GIỚI DI ĐỘNG (ĐÃ SỬA LỖI)
// ======================================================
private String searchTGDD(String keyword) {
    String url = "https://www.thegioididong.com/tim-kiem?key=" + keyword;
    String html = downloadHTML(url);

    StringBuilder sb = new StringBuilder("📱 THẾ GIỚI DI ĐỘNG\n");

    if (html.isEmpty()) {
        sb.append("⚠ Không truy cập được.\n\n");
        return sb.toString();
    }

    // *** ĐÃ SỬA ĐỔI: Sử dụng Regex để tìm kiếm giá trị số trong data-price ***
    // Regex: data-price=\"(\d[\d.,]*)\" tìm kiếm giá trị số trong thuộc tính data-price
    Pattern pattern = Pattern.compile("data-price=\\\"(\\d[\\d.,]*)\\\"");
    Matcher matcher = pattern.matcher(html);
    
    if (matcher.find()) {
        String price = matcher.group(1); 
        return sb.append("💰 Giá: ").append(price).append("₫\n\n").toString();
    }
    
    sb.append("❌ Không tìm thấy giá.\n\n");
    return sb.toString();
}
    // ======================================================
    // ⭐ ĐIỆN MÁY XANH
    // ======================================================
    // ======================================================
// ⭐ ĐIỆN MÁY XANH (ĐÃ SỬA LỖI)
// ======================================================
private String searchDMX(String keyword) {
    String url = "https://www.dienmayxanh.com/tim-kiem?key=" + keyword;
    String html = downloadHTML(url);

    StringBuilder sb = new StringBuilder("🔌 ĐIỆN MÁY XANH\n");

    if (html.isEmpty()) {
        sb.append("⚠ Không truy cập được.\n\n");
        return sb.toString();
    }

    // *** ĐÃ SỬA ĐỔI: Sử dụng Regex để tìm kiếm giá trị số trong data-price ***
    Pattern pattern = Pattern.compile("data-price=\\\"(\\d[\\d.,]*)\\\"");
    Matcher matcher = pattern.matcher(html);
    
    if (matcher.find()) {
        String price = matcher.group(1); 
        return sb.append("💰 Giá: ").append(price).append("₫\n\n").toString();
    }

    sb.append("❌ Không tìm thấy giá.\n\n");
    return sb.toString();
}
    // ======================================================
    // ⭐ FPT SHOP
    // ======================================================
    // ======================================================
// ⭐ FPT SHOP (ĐÃ SỬA LỖI)
// ======================================================
private String searchFPT(String keyword) {
    // FPT Shop dùng gạch ngang (-) thay vì dấu cộng (+) trong URL tìm kiếm
    String url = "https://fptshop.com.vn/tim-kiem/" + keyword; 
    String html = downloadHTML(url);

    StringBuilder sb = new StringBuilder("🟦 FPT SHOP\n");

    if (html.isEmpty()) {
        sb.append("⚠ Không truy cập được.\n\n");
        return sb.toString();
    }

    // *** ĐÃ SỬA ĐỔI: Sử dụng Regex để tìm giá trị số trong thuộc tính meta og:price:amount ***
    // Regex: Tìm số tiền (\d[\d.,]*) trong content="" ngay trước property="og:price:amount"
    Pattern pattern = Pattern.compile("content=\\\"(\\d[\\d.,]*)\\\".*?property=\\\"og:price:amount");
    Matcher matcher = pattern.matcher(html);
    
    if (matcher.find()) {
        String price = matcher.group(1); 
        return sb.append("💰 Giá: ").append(price).append("₫\n\n").toString();
    }

    sb.append("❌ Không tìm thấy giá.\n\n");
    return sb.toString();
}

    // ======================================================
    // MAIN
    // ======================================================
    public static void main(String[] args) {
        new SearchFrame().setVisible(true);
    }
}