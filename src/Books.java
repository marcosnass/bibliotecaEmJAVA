import java.util.ArrayList;
import java.sql.*;
import BookDAO.conexao;

import javax.swing.JOptionPane;


public class Books
{
	Connection con = null;
	ResultSet rs = null;
 	PreparedStatement pst = null;
	
    private String title;
    private String ISBN;
    private String edit;
    private String yearPub;
    private ArrayList<Author> author = new ArrayList<Author>();
    
    public Books(){}

    /*public Books(String ISBN, String title, ArrayList<Author> autor,  String edit, String year) 
    {
    	
        this.author = autor;
        setISBN(ISBN);
        setTitulo(title);
        setEdit(edit);
        setYearPub(year);
    }*/
    
    
    public int sizeAuthor() {
    	int siz;
    	siz = author.size();
    	return siz;
    }
    
    public String getTitle()
    { return title; }

    public ArrayList<Author> getAutor(){
    return author; 
    }

    public String getISBN()
    { return ISBN; }

    public String getEdit()
    { return edit; }

    public String getYearPub()
    { return yearPub; }

    public void setTitulo(String title)
    { this.title = title; }

    
    /*public void setAutor(ArrayList<Author> autor)
    { 
    	this.author = autor; // UTILIZEI O CONSTRUOR DA CLASSE AUTOR AO INV�S DOS M�TODOS SET DA MESMA
    }*/
    
    public void setISBN(String ISBN)
    { this.ISBN = ISBN; }

    public void setEdit(String e)
    { this.edit = e; }

    public void setYearPub(String year)
    { this.yearPub = year; }

    public void addAuthor(Author a) {
    	//author.add(a);
    	con = conexao.conector();
    	String sql = "insert into author(name_author, country, id_book) values ('"+a.getName()+"', '"+a.getCountry()+"', (select id from infoBook order by id desc limit 1))";
	 	try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Author(res) inserido(os) com sucesso");
			}else {
				JOptionPane.showMessageDialog(null, "Ocorreu algum problema na inserção dos dados");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//JOptionPane.showMessageDialog(null, e);
		}
	 	
    	
    }
    
	@Override
	public String toString() {
		return "Titulo = " + title + "\n"
				+ "ISBN = " + ISBN + "\n "
				+ "Editora = " + edit + "\n"
				+ "Ano de Pub/ = " + yearPub;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Books){
			Books book = (Books)obj;
			
			if(!this.getTitle().equals(book.getTitle()) ){ // se o this.getTitle  N�O FOR IGUAL a book.getTitle, retorna false
				return false;	
			}
		} else{//retorna false caso o obj n�o for uma instancia de Books
			return false;
		}

		return true;
	}
	
	
	
}