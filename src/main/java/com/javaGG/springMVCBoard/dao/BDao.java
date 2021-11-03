package com.javaGG.springMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.jstl.sql.Result;
import javax.sql.DataSource;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javaGG.springMVCBoard.dto.BDto;
import com.javaGG.srpingMVCboard.util.Constant;

public class BDao {

JdbcTemplate template;
//   DataSource dataSource;

   public BDao() {
      //super();
      // TODO Auto-generated constructor stub

      this.template = Constant.template;
      
//      try {
//         Context context = new InitialContext();
//         dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
   }

   public ArrayList<BDto> list() {
      String query = "select * from smvc_board order by bGroup desc, bStep asc";
      return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper(BDto.class));
      
//      ArrayList<BDto> dtos = new ArrayList<BDto>();
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//
//      try {
//         conn = dataSource.getConnection();
//         String query = "select * from smvc_board order by bGroup desc, bStep asc";
//         pstmt = conn.prepareStatement(query);
//         rs = pstmt.executeQuery();
//
//         while (rs.next()) {
//            int bId = rs.getInt("bId");
//            String bName = rs.getString("bName");
//            String bTitle = rs.getString("bTitle");
//            String bContent = rs.getString("bContent");
//            Timestamp bDate = rs.getTimestamp("bDate");
//            int bHit = rs.getInt("bHit");
//            int bGroup = rs.getInt("bGroup");
//            int bStep = rs.getInt("bStep");
//            int bIndent = rs.getInt("bIndent");
//
//            BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//            dtos.add(dto);
//         }
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(rs != null) rs.close();
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
//
//
//      return dtos;
   }


   public void write(final String bName, final String bTitle, final String bContent) {
      this.template.update(new PreparedStatementCreator() {
         @Override
         public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            // TODO Auto-generated method stub
            String query="insert into smvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (smvc_board_seq.nextval, ?, ?, ?, 0, smvc_board_seq.currval, 0, 0 )";
            PreparedStatement pstmt =  con.prepareStatement(query);
            pstmt.setString(1, bName);
            pstmt.setString(2, bTitle);
            pstmt.setString(3, bContent);
            return pstmt;
         }
      });

//      Connection conn = null;
//      PreparedStatement pstmt = null;
//
//      try {
//         conn = dataSource.getConnection();
//         String query="insert into smvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (smvc_board_seq.nextval, ?, ?, ?, 0, smvc_board_seq.currval, 0, 0 )";
//         pstmt = conn.prepareStatement(query);
//
//         pstmt.setString(1, bName);
//         pstmt.setString(2, bTitle);
//         pstmt.setString(3, bContent);
//
//         int rn = pstmt.executeUpdate();
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
   }

   public BDto contentView(String strID) {

      UpHit(strID);
      
      String query = "select * from smvc_board where bId =" + strID;
      return  template.queryForObject(query, new BeanPropertyRowMapper(BDto.class));
      
//      BDto dto = null;
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//
//      try {
//         conn = dataSource.getConnection();
//         String query = "select * from smvc_board where bId = ?";
//         pstmt = conn.prepareStatement(query);
//         pstmt.setInt(1, Integer.parseInt(strID));
//         rs = pstmt.executeQuery();
//
//         if (rs.next()) {
//            int bId = rs.getInt("bId");
//            String bName = rs.getString("bName");
//            String bTitle = rs.getString("bTitle");
//            String bContent = rs.getString("bContent");
//            Timestamp bDate = rs.getTimestamp("bDate");
//            int bHit = rs.getInt("bHit");
//            int bGroup = rs.getInt("bGroup");
//            int bStep = rs.getInt("bStep");
//            int bIndent = rs.getInt("bIndent");
//
//            dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//
//         }
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(rs != null) rs.close();
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
//
//      return dto;
   }

      public void modify(final String bId, final String bName,final String bTitle,final String bContent) {
    	           
    	  String query="update smvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
    	  this.template.update(query,new PreparedStatementSetter() {    		          
    		  @Override
    		  public void setValues(PreparedStatement ps) throws SQLException {
    			  
    			  // TODO Auto-generated method stub
    			  ps.setString(1, bName);
    			  ps.setString(2, bTitle);
    			  ps.setString(3, bContent);
    			  ps.setInt(4, Integer.parseInt(bId));
    			  }
    		  });
    	  
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            
//            try {
//               conn = dataSource.getConnection();
//               String query = "update smvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ?";
//               pstmt = conn.prepareStatement(query);
//               
//               pstmt.setString(1,  bName);
//               pstmt.setString(2,  bTitle);
//               pstmt.setString(3,  bContent);
//               pstmt.setInt(4, Integer.parseInt(bId));
//               
//               int rn = pstmt.executeUpdate();
//               
//               
//            } catch (Exception e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//            } finally {         
//               try {
//                  if(pstmt != null) pstmt.close();
//                  if(conn != null) conn.close();
//               } catch (Exception e2) {
//                  // TODO Auto-generated catch block
//                  e2.printStackTrace();
//               }
//            }
         }

      public void delete(final String bId) {
         
          String query="delete from smvc_board where bId = ?";
            this.template.update(query,new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {            	
            	// TODO Auto-generated method stub
            	ps.setInt(1, Integer.parseInt(bId));
        	}
         });
         
//            Connection conn = null;
//            PreparedStatement pstmt = null;
//            
//            try {
//               conn = dataSource.getConnection();
//               String query = "delete from smvc_board where bId = ?";
//               pstmt = conn.prepareStatement(query);
//               
//               pstmt.setInt(1, Integer.parseInt(bId));
//               
//               int rn = pstmt.executeUpdate();
//               
//               
//            } catch (Exception e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//            } finally {         
//               try {
//                  if(pstmt != null) pstmt.close();
//                  if(conn != null) conn.close();
//               } catch (Exception e2) {
//                  // TODO Auto-generated catch block
//                  e2.printStackTrace();
//               }
//            }
      }

   public BDto reply_view(String strID) {
      
      String query = "select * from smvc_board where bId ="+strID;
      return  template.queryForObject(query, new BeanPropertyRowMapper(BDto.class));      
      
//      BDto dto = null;
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//      ResultSet rs = null;
//
//      try {
//         conn = dataSource.getConnection();
//         String query = "select * from smvc_board where bId = ?";
//         pstmt = conn.prepareStatement(query);
//         pstmt.setInt(1, Integer.parseInt(strID));
//         rs = pstmt.executeQuery();
//
//         if (rs.next()) {
//            int bId = rs.getInt("bId");
//            String bName = rs.getString("bName");
//            String bTitle = rs.getString("bTitle");
//            String bContent = rs.getString("bContent");
//            Timestamp bDate = rs.getTimestamp("bDate");
//            int bHit = rs.getInt("bHit");
//            int bGroup = rs.getInt("bGroup");
//            int bStep = rs.getInt("bStep");
//            int bIndent = rs.getInt("bIndent");
//
//            dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
//
//         }
//
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(rs != null) rs.close();
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
//
//      return dto;
   }

   public void replyShape(final String strGroup,final String strStep) {
      
      
      
      String query="update smvc_board set bStep = bStep+1 where bGroup = ? and bStep > ?";
         this.template.update(query,new PreparedStatementSetter() {
         
         @Override
         public void setValues(PreparedStatement ps) throws SQLException {
            // TODO Auto-generated method stub
            ps.setInt(1,Integer.parseInt(strGroup));
            ps.setInt(2,Integer.parseInt(strStep));
         }
      });
      
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//      try {
//         conn = dataSource.getConnection();
//         String query="update smvc_board set bStep = bStep+1 where bGroup = ? and bStep > ?";
//         pstmt = conn.prepareStatement(query);
//
//         pstmt.setInt(1,Integer.parseInt(strGroup));
//         pstmt.setInt(2,Integer.parseInt(strStep));
//         
//         int rn = pstmt.executeUpdate();
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
   }
   
   public void reply(final String bId,final String bName,final String bTitle,final String bContent,final String bGroup,final String bStep,final String bIndent) {

        replyShape(bGroup, bStep);
        
        String query="insert into smvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (smvc_board_seq.nextval, ?, ?, ?,?,?, ? )";
         this.template.update(query,new PreparedStatementSetter() {
         
         @Override
         public void setValues(PreparedStatement ps) throws SQLException {
            // TODO Auto-generated method stub
            ps.setString(1, bName);
            ps.setString(2, bTitle);
            ps.setString(3, bContent);
            ps.setInt(4, Integer.parseInt(bGroup));
            ps.setInt(5, Integer.parseInt(bStep)+1);
            ps.setInt(6, Integer.parseInt(bIndent)+1);   
         }
      });
         
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//
//      try {
//         conn = dataSource.getConnection();
//         String query="insert into smvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (smvc_board_seq.nextval, ?, ?, ?,?,?, ? )";
//         pstmt = conn.prepareStatement(query);
//
//         pstmt.setString(1, bName);
//         pstmt.setString(2, bTitle);
//         pstmt.setString(3, bContent);
//         pstmt.setInt(4, Integer.parseInt(bGroup));
//         pstmt.setInt(5, Integer.parseInt(bStep)+1);
//         pstmt.setInt(6, Integer.parseInt(bIndent)+1);
//
//         int rn = pstmt.executeUpdate();
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
   }
   
   public void UpHit(final String strId) {
      
      String query="update smvc_board set bHit = bHit+1  where bId =  ?";
      
         this.template.update(query,new PreparedStatementSetter() {
            
         @Override
         public void setValues(PreparedStatement ps) throws SQLException {
            // TODO Auto-generated method stub
            ps.setInt(1,Integer.parseInt(strId));

         
            
         }
      });
      
//      Connection conn = null;
//      PreparedStatement pstmt = null;
//      try {
//         conn = dataSource.getConnection();
//         String query="update smvc_board set bHit = bHit+1  where bId =  ?";
//         pstmt = conn.prepareStatement(query);
//
//         pstmt.setInt(1,Integer.parseInt(strId));
//
//         
//
//         int rn = pstmt.executeUpdate();
//
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } finally {         
//         try {
//            if(pstmt != null) pstmt.close();
//            if(conn != null) conn.close();
//         } catch (Exception e2) {
//            // TODO Auto-generated catch block
//            e2.printStackTrace();
//         }
//      }
//      
      
   }

   
}
