package model;

public class Iscritto {
	private String nome,indirizzo,telefono,email;

	public Iscritto(String nome, String indirizzo, String telefono, String email) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}
	

}
