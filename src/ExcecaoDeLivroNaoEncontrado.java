public class ExcecaoDeLivroNaoEncontrado extends Exception {
	String title;
	
	public ExcecaoDeLivroNaoEncontrado() {}
	
	public ExcecaoDeLivroNaoEncontrado(String t) {
		super();
		this.title = t;
	}
	

	@Override
	public String toString() {
		return "Este t�tulo n�o foi encontrado!!";
	}
}