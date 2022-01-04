package servlet;

import jakarta.servlet.annotation.WebServlet;
import javabean.db_conn;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author shkstart
 * @create 2022-01-2022/1/4-12:58
 */
@WebServlet("/upload_img")
public class upload_file extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("index/edit_info.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=request.getSession();
        if(session.getAttribute("user_id")==null) {
            response.sendRedirect("index/user_info.jsp");
        }
        else {
            //request.setCharacterEncoding("utf-8");
            boolean flag= ServletFileUpload.isMultipartContent((javax.servlet.http.HttpServletRequest) request);
            if(flag) {
                DiskFileItemFactory factory=new DiskFileItemFactory();
                ServletFileUpload upload=new ServletFileUpload(factory);
                try {
                    List<FileItem> fileList = upload.parseRequest((javax.servlet.http.HttpServletRequest) request);
                    Iterator<FileItem> myitor = fileList.iterator();
                    while(myitor.hasNext()) {
                        FileItem item=myitor.next();
                        if(item!=null) {
                            String filename=item.getName();
                            if(filename!=null) {
                                String path="index/upload";
                                String absolutepath=this.getServletContext().getRealPath(path);

                                File file = new File(filename);
                                String file_name=file.getName();
                                String ext=file_name.substring(file_name.lastIndexOf("."));
                                String end_filename= UUID.randomUUID().toString()+ext;
                                File uploadFile = new File(absolutepath, end_filename);
                                try {
                                    if(uploadFile.exists()) {
                                        uploadFile.delete();
                                    }
                                    item.write(uploadFile);

                                    db_conn conn= new db_conn();
                                    String sql="update common_user set avatar_img='"+end_filename+"' "
                                            + "where user_name='"+session.getAttribute("user_id")+"'";
                                    int res=conn.Update(sql);
                                    //System.out.println(res);
                                    PrintWriter out=response.getWriter();
                                    response.setContentType("text/html;charset=utf-8");
                                    if(res!=0) {
                                        response.sendRedirect("index/user_info.jsp");
                                    }else {

                                        out.println("文件上传失败，程序出现bug，请联系开发人员修复bug");
                                        response.setHeader("refresh", "2;url=index/user_info.jsp");
                                    }





                                }catch (Exception e) {
                                    System.out.println("出错了1，出错信息如下："+e);
                                }

                            }
                        }
                    }
                } catch (FileUploadException e) {
                    System.out.println("出错了2，出错信息如下："+e);
                    e.printStackTrace();
                }
            }
        }

    }
}
