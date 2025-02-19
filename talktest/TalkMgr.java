package talktest;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import member.DBConnectionMgr;
import member.MemberBean;

public class TalkMgr {
   private DBConnectionMgr pool;
   public TalkMgr() {
      pool = DBConnectionMgr.getInstance();
   }
   //�α���
   //select count(id) from tblRegister where id = ? and pwd = ?
   public boolean loginChk(String id, String pwd) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      boolean flag = false;
      try {
         con = pool.getConnection();
         sql = "select count(id) from tblRegister where id = ? and pwd = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, pwd);
         rs = pstmt.executeQuery();
         if(rs.next()&&rs.getInt(1)==1)
            flag = true;
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         pool.freeConnection(con, pstmt, rs);
      }
      return flag;
      
   }
 
}