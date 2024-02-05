package com.delivery.domain.storefile.entity;

import com.delivery.domain.owner.entity.OwnerEntity;
import com.delivery.domain.store.entity.StoreEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Table(name = "store_file")
@Entity
@Getter
@Setter
public class StoreFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreEntity storeEntity;

    private String orgNm;

    private String savedNm;

    private String savedPath;

    @Builder
    public StoreFileEntity(Long id, String orgNm, String savedNm, String savedPath, StoreEntity storeEntity) {
        this.id = id;
        this.storeEntity = storeEntity;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
    }
}