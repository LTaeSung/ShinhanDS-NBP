package com.delivery.domain.menufile.entity;

import com.delivery.domain.menu.entity.MenuEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Table(name = "menu_file")
@Entity
@Getter
@Setter
public class MenuFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menuEntity;

    private String orgNm;

    private String savedNm;

    private String savedPath;

    @Builder
    public MenuFileEntity(Long id, String orgNm, String savedNm, String savedPath, MenuEntity menuEntity) {
        this.id = id;
        this.menuEntity = menuEntity;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
    }
}