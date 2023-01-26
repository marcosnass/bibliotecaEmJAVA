public class ExcecaoDeLivroJaExistente extends Exception{
	String title;
	
	public ExcecaoDeLivroJaExistente() {}
	
	public ExcecaoDeLivroJaExistente(String t) {
		super();
		this.title = t;
	}
	

	@Override
	public String toString() {
		return "Livro já possui um registro com esse título: " + this.title;
	}
}
