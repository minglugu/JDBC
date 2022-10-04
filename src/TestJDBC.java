import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class TestJDBC {
    public static void main(String[] args) {
        // low coupling
        // 向上转型 upcasting
        DataSource dataSource = new MysqlDataSource();

        // 向下转型 downcasting
        // set the address of databse, 设置数据库所在的地址
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://127.0.0.1:3306/java102?characterEncoding=utf8&useSSL=false");
        // set user name to login the db
        ((MysqlDataSource) dataSource).setUser();
        // set password to login the db
        ((MysqlDataSource) dataSource).setPassword();


    }
}
