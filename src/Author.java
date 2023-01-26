public class Author
{
	private String name;
	private String country;
	
	public Author(){}
	
	public Author(String name, String country) {
		super();
		setName(name);
		setCountry(country);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Nome = " + name + " - Pa�s de Origem = " + country + " \n ";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Author){
			Author author = (Author)obj;
			
			if(this.getName().equals(author.getName()) ){ // se this,getName(), for igual ao obj
				return true;	
			}
		} else{//retorna false caso o obj n�o for uma instancia de Author
			return false;
		}

		return false;
	}
	
}
