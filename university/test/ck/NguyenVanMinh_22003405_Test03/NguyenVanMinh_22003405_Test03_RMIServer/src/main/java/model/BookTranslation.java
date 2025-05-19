package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
@Entity
@Table(name = "book_translations")
public class BookTranslation extends Book implements Serializable {
    @Column(name = "translate_name")
    private String translationName;

    private String language;
}
