import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import java.sql.*;
import BookDAO.conexao;
public class Main {

	public static void main(String[] args) throws Exception {
			
		 	Libary biblio = new Libary();
		 	Books book = new Books();
	        
	        int opcao = 0;
	        String opcoes =
	          "             !!--== MENU ==-- !!\n"+
	          "\n1. Cadastrar Livro"+
	          "\n2. Pesquisar Livro pelo nome"+
	          "\n3. Pesquisar Livro (pelo ano)"+
	          "\n4. Listar Livros"+
	          "\n5. Remover livro:"+
	          "\n6. Remover pelo ID"+
	          "\n7. Ordenar em ordem alfabética"+
	          "\n8. Finalizar"+
	          "\n\nSelecione a opção: ";
	        
	        
	        while (opcao != 8)
	        {
	            opcao = Integer.parseInt(JOptionPane.showInputDialog(null,opcoes));
	            
	            switch(opcao)
	            {
	              case 1:
	            	  	int size = biblio.size();
	            	  	Boolean answer = false;
	            	  	Books newBook = typeBook();
	            	  	
	            		try {
	                		if(size == 0) {
	                			biblio.includeBook(newBook);
	                			JOptionPane.showMessageDialog(null, "Livro Cadastrado!!");
	                			JOptionPane.showMessageDialog(null, "Agora vamos ao(s) autor(res)!!");
	                			infoAuthor();
	                			break;
	                		}
	                		
                			if(size > 0) {
	                		  for (int i=0;i<size;i++)
			                  {
	                			  book = biblio.getBook(i);
			                      boolean equal = book.getTitle().equals(newBook.getTitle());//implementa��o da sobrescrita equals() da classe Books
			                      if(equal) {
			                    	  answer = true;
			                    	  break;
			                      }
			                  }
	                		} 
                		if(answer) {
                			throw new ExcecaoDeLivroJaExistente(newBook.getTitle());
                		}else {
                			    biblio.includeBook(newBook);
	                			JOptionPane.showMessageDialog(null, "Livro Cadastrado!!");
	                			break;
                		}
	                		
	                	}catch(ExcecaoDeLivroJaExistente e) {
	                		JOptionPane.showMessageDialog(null, e.toString());
	                	}
	            		break;
	              
	              case 2:
	            	  size = biblio.size();
	            	
	            	  answer = false; 
	            	  book.setTitulo(JOptionPane.showInputDialog("Pesquisar pelo nome: ").toLowerCase());
	            	  JOptionPane.showMessageDialog(null, "Pesquisa realacionada ao nome dado");
          			  biblio.getByTitle(book);
          			  showBook(book);
          			
		                break;
	            		/*try {
	                		
	                		if(size == 0) {
	                			JOptionPane.showMessageDialog(null, "Pesquisa realacionada ao nome dado");
	                			biblio.getByTitle(name);
	                			
	     		                break;
	                		}
	                		
	          			if(size > 0) {
	                		  for (int i=0;i<size;i++)
			                  {
	                			  book = biblio.getBook(i);
			                      boolean equal = book.getTitle().equals(newBook.getTitle());//implementa��o da sobrescrita equals() da classe Books
			                      if(equal) {
			                    	  answer = true;
			                    	  break;
			                      }
			                  }
	                		} 
	              		if(answer == true) {
	              			throw new ExcecaoDeLivroJaExistente(newBook.getTitle());
	              		}else {
	              			 biblio.getByTitle(name);
	 		                JOptionPane.showMessageDialog(null, "Livro Cadastrado na primeira posi��o!!");
	 		                break;
	              		}
		                		
		                	}catch(ExcecaoDeLivroJaExistente e) {
		                		JOptionPane.showMessageDialog(null, e.toString());
		                	}*/
	            	 
	              case 3:	
	            	 int yearA, yearB;
            	      yearA = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano inicial"));
            	      yearB = Integer.parseInt(JOptionPane.showInputDialog("Informe o ano final"));
	            	 StringBuffer list = new StringBuffer();
	            	 
            	      for(Books b : biblio.getBookYear(yearA, yearB)) {
            	    	  //showBook(b);
            	    	  list.append("Title: " + b.getTitle() + "\n");
            	    	  list.append("ISBN: " + b.getISBN() + "\n");
            	    	  list.append("Editora: " + b.getEdit() + "\n");
            	    	  list.append("Ano: " + b.getYearPub() + "\n");
            	    	  list.append("---------------\n");
            	      }
            	      JOptionPane.showMessageDialog(null, list);
            	      
            	      
            	      
		          break; 
	              case 4:
	            	  StringBuffer listBooks = new StringBuffer();
		            	 
            	      for(Books b : biblio.getAllBooks()) {
            	    	  //showBook(b);
            	    	  listBooks.append("Title: " + b.getTitle() + "\n");
            	    	  listBooks.append("ISBN: " + b.getISBN() + "\n");
            	    	  listBooks.append("Editora: " + b.getEdit() + "\n");
            	    	  listBooks.append("Ano: " + b.getYearPub() + "\n");
            	    	  listBooks.append("---------------\n");
            	      }
            	      JOptionPane.showMessageDialog(null, listBooks);
	                  break;
	              case 5:
	              {		 String title = JOptionPane.showInputDialog("Nome do livro que deseja excluir?");
            			 biblio.removeBook(title);
            			 JOptionPane.showMessageDialog(null, "Livro foi removido!");
			                  
	              } 	 
			          break; 
	              case 6:
	              {		
	            	 int id = Integer.parseInt(JOptionPane.showInputDialog("Qual id do livro que deseja remover?"));
         			 biblio.removeById(id);
         			 JOptionPane.showMessageDialog(null, "Livro foi removido!");
	              }
		          break; 
	              case 7:
		              {
		            	  StringBuffer orderByTitle = new StringBuffer();
			            	 
	            	      for(Books b : biblio.orderByTitle()) {
	            	    	  //showBook(b);
	            	    	  orderByTitle.append("Title: " + b.getTitle() + "\n");
	            	    	  orderByTitle.append("ISBN: " + b.getISBN() + "\n");
	            	    	  orderByTitle.append("Editora: " + b.getEdit() + "\n");
	            	    	  orderByTitle.append("Ano: " + b.getYearPub() + "\n");
	            	    	  orderByTitle.append("---------------\n");
	            	      }
	            	      JOptionPane.showMessageDialog(null, orderByTitle);
			      			break;
		              }
	            	}
	            }
	        JOptionPane.showMessageDialog(null, "Programa finalizado,\n" + "volte logo!");
	        }
	        
	    
		static Author infoAuthor() {
			 Books liv = new Books();
			 Author author = new Author();
			 
		     int op = Integer.parseInt(JOptionPane.showInputDialog("Quantos autores o livro possui?"));
		     String name;
     		 String country; 
		        if(op > 1) {
		        	int cont = 1;
		        	while(op != 0) {
		       
		        		name = JOptionPane.showInputDialog("Nome do " + cont + "º Autor").toLowerCase();
		        		country = JOptionPane.showInputDialog("País de origem do " + cont + " º Autor").toLowerCase();
		        		author = new Author(name, country);
		        		liv.addAuthor(author);
		        		cont++;
			        	op--;
		        	}
		        }else {
		        	name = JOptionPane.showInputDialog("Qual o nome do Autor?").toLowerCase();
	        		country = JOptionPane.showInputDialog("Seu pais de origem?").toLowerCase();
	        		//nameAux1 = name.replace(" ", "");
	        		
	        		author = new Author(name, country);
	        		liv.addAuthor(author);
		        }
		        return author;
		}
	
	    static Books typeBook()
	    {	
	    	Books liv = new Books();
	        
	        liv.setTitulo(JOptionPane.showInputDialog("T�tulo do Livro:").toLowerCase()); // setando o t�tulo
	        liv.setISBN(JOptionPane.showInputDialog("N�mero ISBN do Livro:"));
	        liv.setYearPub(JOptionPane.showInputDialog("Ano da publica��o:"));
	        liv.setEdit(JOptionPane.showInputDialog("Nome da Editora").toLowerCase());
	       
	        return liv;
	    }
	       
	    static void showBook(Books x)
	    {
	        String texto = "Livro:\n"+
	        "\nT�tulo: "+x.getTitle()+
	        "\nAutor: "+x.getAutor()+
	        "\nISBN: "+x.getISBN()+
	        "\nAnoPubli: "+x.getYearPub()+
	        "\nEditora: "+x.getEdit();
	        JOptionPane.showMessageDialog(null, texto);
	    }	
	}
