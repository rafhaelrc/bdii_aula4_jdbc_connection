package model;
import java.time.LocalDate;

public class Cliente {

    private Integer id; // n_numeclien
    private String codigo; // c_codiclien
    private String nome; // c_nomeclien
    private String razaoSocial; // c_razaclien
    private LocalDate dataCadastro; // d_dataclien
    private String cnpj; // c_cnpjclien
    private String telefone; // c_foneclien
    private String cidade; // c_cidaclien
    private String estado; // c_estaclien


    public Cliente() {}

    public Cliente(Integer id, String codigo, String nome, String razaoSocial, LocalDate dataCadastro, String cnpj, String telefone, String cidade, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.dataCadastro = dataCadastro;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
