package mdeis.module1.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable {
    @CreatedBy
    @Column(name = "created_by", updatable = false, nullable = false)
    int createdBy = 1;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreatedDate
    @CreationTimestamp
    Timestamp createdAt = null;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    Integer lastModifiedBy = 1;

    @Column(name = "updated_at")
    @LastModifiedDate
    @UpdateTimestamp
    Timestamp updatedAt;

    @Column(name = "deleted_at")
    Timestamp deletedAt;
}
