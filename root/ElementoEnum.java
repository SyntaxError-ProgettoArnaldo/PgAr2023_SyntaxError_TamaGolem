package root;

public enum ElementoEnum
{
    PIETRA("Pietra1", new int[]{1, 2});
    private String nome;
    private int[] equilibriSingoli;

    ElementoEnum(String nome, int[] equilibriSingoli)
    {
        this.nome = nome;
        this.equilibriSingoli = equilibriSingoli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getEquilibriSingoli() {
        return equilibriSingoli;
    }

    public void setEquilibriSingoli(int[] equilibriSingoli) {
        this.equilibriSingoli = equilibriSingoli;
    }
}
