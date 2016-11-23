package springMVC.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import springMVC.aop.PersonServer;
import springMVC.aop.PersonServiceBean;
import springMVC.bean.IPage;
import springMVC.bean.MVCUser;
import springMVC.service.LoginService;

@Controller
@RequestMapping("/auth")
public class authAction extends AbstractController {

	@Resource(name = "loginService")
	protected LoginService loginService;
	
	// aop测试类
	@Resource(name = "personServer")
	protected PersonServer personServer;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "opennew")
	public Map<String, Object> opennew(Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msg", "pass");

		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "aop")
	public Map<String, Object> aop(Model model) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("msg", "aop");

		/*PersonServer p = new PersonServiceBean();
		p.save("zjj");*/
		personServer.save("zjj");
		
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "upload")
	public ModelAndView upload(
			@RequestParam(value = "files") MultipartFile[] files, Model model,
			HttpServletRequest request) {
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/");

		for (int i = 0; i < files.length; i++) {
			MultipartFile multipartFile = files[i];
			File file = new File(path + multipartFile.getOriginalFilename());
			if (!file.exists()) {
				try {
					file.createNewFile();
					multipartFile.transferTo(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("uploadForm");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "fileForm")
	public ModelAndView fileForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("uploadForm");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "importFileForm")
	public ModelAndView importFileForm(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("importUploadForm");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "bdmap")
	public ModelAndView bdmap(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("bdmap");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "download")
	public String download(String filename, HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ filename);
		try {
			@SuppressWarnings("deprecation")
			String path = request.getRealPath("/");
			InputStream inputStream = new FileInputStream(new File(path
					+ filename));

			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}

			// 这里主要关闭。
			os.close();

			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 返回值要注意，要不然就出现下面这句错误！
		// java+getOutputStream() has already been called for this response
		return null;
	}

	@RequestMapping(value = "export")
	@ResponseBody
	public String export(MVCUser user, /*int page, int rows,*/String filename, String conditions,
			HttpServletRequest request, HttpServletResponse response) {
		IPage ipage = loginService.list(user, 1, 1, conditions, "", "");
		@SuppressWarnings("unchecked")
		List<MVCUser> users = (List<MVCUser>) ipage.getRows();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ filename);
		
		WritableWorkbook wwb;
        try {    
        	OutputStream os = response.getOutputStream();
            wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet("users1", 2);        // 创建一个工作表

            //    设置单元格的文字格式
            WritableFont wf = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,
                    UnderlineStyle.NO_UNDERLINE,Colour.BLUE);
            WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            ws.setRowView(1, 500);

            //    填充数据的内容
            for (int i = 0; i < users.size(); i++){
            	MVCUser curuser = users.get(i);
                //ws.addCell(new WritableCell(1, i + 1, p[i].getName(), wcf));
            	//                   列         行
                ws.addCell(new Label(0, i + 1, curuser.getId(), wcf));
                ws.addCell(new Label(1, i + 1, curuser.getName(), wcf));
                
                /*if(i == 0)
                    wcf = new WritableCellFormat();*/
            }

            wwb.write();
            wwb.close();

        } catch (IOException e){
        } catch (RowsExceededException e){
        } catch (WriteException e){}
		
		return null;
	}
	
	@RequestMapping(value = "import")
	@ResponseBody
	public ModelAndView importExcel(@RequestParam(value = "files") MultipartFile[] files){
		for (int i = 0; i < files.length; i++) {
			File file = new File("user");
			try {
				files[i].transferTo(file);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Vector<MVCUser> v = resolveExcel(file);
			
			for (int j = 0; j < v.size(); j++) {
				MVCUser u = v.get(j);
				System.out.println(u.getId());
				System.out.println(u.getName());
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("importUploadForm");
		return modelAndView;
	}
	
	
	 /** *//**
     * 从Excel文件里读取数据保存到Vector里
     * @param fileName        Excel文件的名称
     * @return                Vector对象,里面包含从Excel文件里获取到的数据
     */
    public Vector<MVCUser> resolveExcel(File file){
        Vector<MVCUser> v = new Vector<MVCUser>();
        try {
            Workbook book = Workbook.getWorkbook(file);
            Sheet sheet = book.getSheet(0);        // 获得第一个工作表对象 
            int rows = sheet.getRows();
            
            for(int i = 0; i < rows; i++) {
                Cell [] cell = sheet.getRow(i);
                if(cell.length == 0)
                    continue;
                
                MVCUser p = new MVCUser();
                p.setId(sheet.getCell(0, i).getContents());
                p.setName(sheet.getCell(1, i).getContents());
                
                v.add(p);
            }

            book.close();
        }catch(Exception e) {} 
        return v;
    }
}
