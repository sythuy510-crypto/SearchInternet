🔎 Web Scraper Application (Java Swing)
This project is a simple desktop application developed using Java Swing for the graphical user interface (GUI). Its primary function is to perform basic web scraping to find information and product pricing from several Vietnamese news and e-commerce websites.
✨ Features
• 📰 Content Search: Search for keywords across major Vietnamese news sites (Dantri, VnExpress, 24h, ZingNews) and display matching sentences.
• 💰 Price Search: Search for product prices (e.g., "soundcore R50i") on leading Vietnamese e-commerce and retail sites.
• Supported Sites: The Gioi Di Dong (TGDD), Dien May Xanh (DMX), FPT Shop, Shopee, Tiki, Lazada.
• 🌐 Raw HTML Retrieval: Uses standard Java networking libraries (HttpURLConnection) to download and process raw HTML content.
• 🗑️ Robust HTML Stripping: Implements custom logic using Regular Expressions (Regex) to strip HTML tags and common web artifacts (like cdnphoto, data-price, zoom) to extract plain text.
⚙️ Technologies Used
• Language: Java
• GUI Library: Java Swing
• Networking: java.net.HttpURLConnection
• Data Extraction: java.util.regex (Regular Expressions)
🚀 Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
Prerequisites
You need to have the following software installed:
• Java Development Kit (JDK) 8 or higher
• An Integrated Development Environment (IDE) like NetBeans, Eclipse, or IntelliJ IDEA
📖 How to Use
1. Select Websites: Use the left panel to select the websites you want to query.
2. Enter Keywords: Input your search terms in the "Từ khóa 1," "Từ khóa 2," and "Từ khóa 3" fields.
3. Choose Action:
• Click "Tìm kiếm nội dung" (Search Content) to look for matching sentences.
• Click "Tìm giá sản phẩm" (Search Product Price) to fetch prices for the item in "Từ khóa 1".
⚠️ Important Disclaimer (Web Scraping)
This project uses raw string parsing and Regular Expressions to extract data. Web scraping is inherently fragile:
• Website Changes: If a target website changes its HTML structure, CSS classes, or networking protocols, the price/content extraction logic will break (which is the source of the issues shown in the screenshots).
• Scalability: For production environments, it is strongly recommended to use a dedicated HTML parsing library (like Jsoup in Java) for reliable and maintainable scraping.
🤝 Contribution
Contributions are welcome! If you find a bug or have an enhancement idea (especially improving the HTML parsing robustness), please open an issue or submit a pull request.
📝 License
This project is licensed under the MIT License - see the LICENSE.md file for details.
👨‍💻 Author
• [NguyenSyThuy] - Initial work - [(https://github.com/sythuy510-crypto)]
