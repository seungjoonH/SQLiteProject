import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		Connection con = null;
		
		try {
			// SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			// SQLite DB 파일에 연결
			String dbFile = "myfirst.db";
			
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			Statement stat = con.createStatement();
			
			// 데이터 추가
			System.out.println("\n *** 새 데이터 6개 추가 ***");
			String sqls[] = {
				"insert into g_artists (name,  a_type,  a_year,  debut,  regdate) "
				+ "values ('아이유', '여성', '2000년대', '2008년 / 미아', datetime('now', 'localtime'));", 
				"insert into g_artists (name,  a_type,  a_year,  debut,  regdate) "
				+ "values ('거미', '여성', '2000년대,  2010년대,  2020년대', '2003년 / 그대 돌아오면', datetime('now', 'localtime'));", 
				"insert into g_artists (name,  a_type,  a_year,  debut,  regdate) "
				+ "values ('방탄소년단', '남성', '2010년대', '2013년 / No More Dream', datetime('now', 'localtime'));", 
				"insert into g_artists (name,  a_type,  a_year,  debut,  regdate) "
				+ "values ('이무진', '남성', '2010년대,  2020년대', '2018년 / 누구없소', datetime('now', 'localtime'));", 
				"insert into g_artists (name,  a_type,  a_year,  debut,  regdate) "
				+ "values ('악뮤(AKMU)', '혼성(듀엣)', '2010년대,  2020년대', '2012년 / 다리꼬지마', datetime('now', 'localtime'));", 
				"insert into g_artists (name,  a_type,  regdate) "
				+ "values ('테스트 가수', '유형', datetime('now', 'localtime'));"
			};
			
			int cntCre = 0;
			boolean isError = false;
			
			for (String sql : sqls) {
				cntCre = stat.executeUpdate(sql);
				if (cntCre == 0) { System.out.println("[Error] 데이터 추가 오류"); isError = true; break; }
			}
			if (!isError) System.out.println("새로운 데이터가 추가되었습니다!");
			
			
			// 데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			String sqlVis = "select * from g_artists";
			ResultSet rsVis = stat.executeQuery(sqlVis);
			
			while (rsVis.next()) {
				String id = rsVis.getString("id");
				String name = rsVis.getString("name");
				String a_type = rsVis.getString("a_type");
				String a_year = rsVis.getString("a_year");
				String debut = rsVis.getString("debut");
				String regdate = rsVis.getString("regdate");
				System.out.println(String.format("%s | %s | %s | %s | %s | %s", id, name, a_type, a_year, debut, regdate));
			}
			
			
			// 데이터 수정
			System.out.println("\n *** 데이터 수정 ***");
			String sqlUpd = "update g_artists set a_year = '2000년대, 2010년대, 2020년대' where id=1;";
			
			int cntUpd = stat.executeUpdate(sqlUpd);
			if (cntUpd > 0) System.out.println("데이터가 수정 되었습니다!");
			else System.out.println("[Error] 데이터 수정 오류");
			
			
			// 데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			rsVis = stat.executeQuery(sqlVis);
			
			while (rsVis.next()) {
				String id = rsVis.getString("id");
				String name = rsVis.getString("name");
				String a_type = rsVis.getString("a_type");
				String a_year = rsVis.getString("a_year");
				String debut = rsVis.getString("debut");
				String regdate = rsVis.getString("regdate");
				System.out.println(String.format("%s | %s | %s | %s | %s | %s", id, name, a_type, a_year, debut, regdate));
			}
			
			
			// 데이터 삭제
			System.out.println("\n *** 데이터 삭제 ***");
			String sqlDel = "delete from g_artists where id=6;";
			
			int cntDel = stat.executeUpdate(sqlDel);
			if (cntDel > 0) System.out.println("데이터가 삭제 되었습니다!");
			else System.out.println("[Error] 데이터 삭제 오류");
			
			
			// 데이터 조회
			System.out.println("\n*** 데이터 조회 ***");
			rsVis = stat.executeQuery(sqlVis);
			
			while (rsVis.next()) {
				String id = rsVis.getString("id");
				String name = rsVis.getString("name");
				String a_type = rsVis.getString("a_type");
				String a_year = rsVis.getString("a_year");
				String debut = rsVis.getString("debut");
				String regdate = rsVis.getString("regdate");
				System.out.println(String.format("%s | %s | %s | %s | %s | %s", id, name, a_type, a_year, debut, regdate));
			}
			
			stat.close();
			
		}
		catch (Exception e) { e.printStackTrace(); }
		finally { if (con != null) { try { con.close(); } catch (Exception e) {} } }
	}

}
