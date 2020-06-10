package com.ineor.countrypopulation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Page {
    @JsonProperty(value = "page")
    private Integer currentPage;
    @JsonProperty(value = "pages")
    private Integer totalPages;
    @JsonProperty(value = "per_page")
    private Integer itemsPerPage;

    public Page() {
        this.currentPage = 1;
        this.itemsPerPage = 50;
        this.totalPages = Integer.MAX_VALUE;
    }

    public Page(Integer itemsPerPage) {
        this.currentPage = 1;
        this.itemsPerPage = itemsPerPage == null || itemsPerPage < 50 ? 50 : itemsPerPage;
        this.totalPages = Integer.MAX_VALUE;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
