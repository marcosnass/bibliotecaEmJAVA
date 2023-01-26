import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import BookDAO.conexao;

import javax.swing.JOptionPane;

public class Libary
{
	Connection con = null;
	ResultSet rs = null;
 	PreparedStatement pst = null;
    ArrayList<Books> list = new ArrayList<Books>();
    ArrayList<Author> list_author = new ArrayList<Author>();
	
   
    public Libary()
    {
    }
   
    public ArrayList<Books> getBooks() {
    	return list;
    }
    
    public Books getBook(int pos) {
    	
    	return list.get(pos);
    }
    
   
    public int size() {
    	int siz;
    	siz = list.size();
    	return siz;
    }
    
    public void includeBook(Books book)
    {	
    	//list.add(book);
    	con = conexao.conector();
    	String sql = "insert into infoBook (title, isbn, edit, yearpub) values ('"+ book.getTitle() +"', '"+book.getISBN()+"', '"+book.getEdit()+"', '"+book.getYearPub()+"')";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Livro Inserido com sucesso");
			}else {
				JOptionPane.showMessageDialog(null, "Ocorreu algum problema na inserção dos dados");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}
	 	
    	
    	
    	
    }
    
    public void getByTitle(Books b)
    {
    	//list.add(pos, book);
    	ResultSet rs = null;
    	Author a = new Author();
    	con = conexao.conector();
    	String sql = "select * from author inner join infoBook on author.id_book = infoBook.id where title = '"+b.getTitle()+"'";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				//int count = rs.getString("name_author").length();
				b.setTitulo(rs.getString("title"));
				b.setISBN(rs.getString("isbn"));
				b.setEdit(rs.getString("edit"));
				b.setYearPub(rs.getString("yearpub"));
				//JOptionPane.showMessageDialog(null, "Quantidade de autores " + count_author);
				JOptionPane.showMessageDialog(null, "Livro encontrado");
			}else {
				JOptionPane.showMessageDialog(null, "Livro não encontrado");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}
	 	//return rs;
    }
    
    
    public void removeBook(String title)
    {	
    	con = conexao.conector();
    	
    	String sql = "delete from infoBook where  title = '"+title+"'";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}finally {
			if(rs != null && pst!=null && con!=null ) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
    public void removeById(int id)
    {	
    	con = conexao.conector();
    	
    	String sql = "delete from infoBook where id = "+id+"";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}finally {
			if(rs != null && pst!=null && con!=null ) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }
    
    public void removeInPos(int pos)
    {	
        list.remove(pos);
   
    }
    
    public void getBookLetterKey(String tit) throws ExcecaoDeLivroNaoEncontrado
    {	
    	String[] wB;
    	StringBuffer lista = new StringBuffer();
        
    	lista.append("Lista de livros encontrado com a palavra chave passada:\n");
    	
		for(Books book : list) { 
	    wB = book.getTitle().split(" ");
		for(String letter : wB) {
			//usando a sobrescrita equals da classe Books
			boolean equal = tit.equals(letter);
			// a vari�vel equal ir� retornar true se for iguais ou false caso n�o seja iguais.
			if(equal) {
				lista.append(
		        "\nT�tulo: "+book.getTitle()+
		        "\nEditora: "+book.getEdit()+
		        "\nAutor: "+book.getAutor());
			}
		}
		}
		try {
		if( lista.length() == 56) {//o tamanho deve ser igaul a 56 devido ao primeiro append feito antes do for. Caso maior tem livro dentro da lista
			throw new ExcecaoDeLivroNaoEncontrado();
		}else {
		JOptionPane.showMessageDialog(null, lista.toString());
		}
		}catch(ExcecaoDeLivroNaoEncontrado e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}
    }
    
    
    public List<Books> getBookYear(int yearA, int yearB) {
    	
    	con = conexao.conector();
    	List<Books> list = new ArrayList<>();
    	
    	String sql = "select title, isbn, edit, yearpub from infoBook where yearpub between '"+yearA+"' and '"+yearB+"'";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
					Books b = new Books();
        			b.setTitulo(rs.getString("title"));
        			b.setISBN(rs.getString("isbn"));
        			b.setEdit(rs.getString("edit"));
        			b.setYearPub(rs.getString("yearpub"));
        			list.add(b);
		    	
	    	}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}finally {
			if(rs != null && pst!=null && con!=null ) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	 	return list;
    }
    
 public List<Books> getAllBooks() {
    	
    	con = conexao.conector();
    	List<Books> list = new ArrayList<>();
    	
    	String sql = "select * from infoBook";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
					Books b = new Books();
        			b.setTitulo(rs.getString("title"));
        			b.setISBN(rs.getString("isbn"));
        			b.setEdit(rs.getString("edit"));
        			b.setYearPub(rs.getString("yearpub"));
        			list.add(b);
		    	
	    	}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}finally {
			if(rs != null && pst!=null && con!=null ) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	 	return list;
    }
 public List<Books> orderByTitle(){
	 con = conexao.conector();
 	List<Books> list = new ArrayList<>();
 	
 	String sql = "select * from infoBook order by title";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
					Books b = new Books();
     			b.setTitulo(rs.getString("title"));
     			b.setISBN(rs.getString("isbn"));
     			b.setEdit(rs.getString("edit"));
     			b.setYearPub(rs.getString("yearpub"));
     			list.add(b);
		    	
	    	}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}finally {
			if(rs != null && pst!=null && con!=null ) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	 	return list;
 }
    
}






