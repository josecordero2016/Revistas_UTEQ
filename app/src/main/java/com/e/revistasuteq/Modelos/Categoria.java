package com.e.revistasuteq.Modelos;

public class Categoria {
    private String section, section_id;

    public Categoria(String section, String section_id) {
        this.section = section;
        this.section_id = section_id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }
}
