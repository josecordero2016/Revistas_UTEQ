package com.e.revistasuteq.Modelos;

public class Articulo {

    private String section, publication_id, title, doi, date_published, section_id;
    private Galeys[] galeys;
    private Authors[] authors;

    public Articulo(String section, String publication_id, String title, String doi, String date_published, String section_id, Galeys[] galeys, Authors[] authors) {
        this.section = section;
        this.publication_id = publication_id;
        this.title = title;
        this.doi = doi;
        this.date_published = date_published;
        this.section_id = section_id;
        this.galeys = galeys;
        this.authors = authors;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPublication_id() {
        return publication_id;
    }

    public void setPublication_id(String publication_id) {
        this.publication_id = publication_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public Galeys[] getGaleys() {
        return galeys;
    }

    public void setGaleys(Galeys[] galeys) {
        this.galeys = galeys;
    }

    public Authors[] getAuthors() {
        return authors;
    }

    public void setAuthors(Authors[] authors) {
        this.authors = authors;
    }

    public class Galeys{
        private int galley_id;
        private String label, UrlViewGalley;

        public Galeys(int galley_id, String label, String urlViewGalley) {
            this.galley_id = galley_id;
            this.label = label;
            UrlViewGalley = urlViewGalley;
        }

        public int getGalley_id() {
            return galley_id;
        }

        public void setGalley_id(int galley_id) {
            this.galley_id = galley_id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getUrlViewGalley() {
            return UrlViewGalley;
        }

        public void setUrlViewGalley(String urlViewGalley) {
            UrlViewGalley = urlViewGalley;
        }
    }

    public class Authors{
        private String nombres, filiacion, email;

        public Authors(String nombres, String filiacion, String email) {
            this.nombres = nombres;
            this.filiacion = filiacion;
            this.email = email;
        }

        public String getNombres() {
            return nombres;
        }

        public void setNombres(String nombres) {
            this.nombres = nombres;
        }

        public String getFiliacion() {
            return filiacion;
        }

        public void setFiliacion(String filiacion) {
            this.filiacion = filiacion;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
