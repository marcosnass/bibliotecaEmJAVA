public class ExcecaoDeLivroJaExistente extends Exception{
	String title;
	
	public ExcecaoDeLivroJaExistente() {}
	
	public ExcecaoDeLivroJaExistente(String t) {
		super();
		this.title = t;
	}
	

	@Override
	public String toString() {
		return "Livro j� possui um registro com esse t�tulo: " + this.title;
	}
}
