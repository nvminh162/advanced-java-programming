package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.JOINED)
public class Book implements Serializable {
    @Id
    @Column(nullable = false)
    protected String ISBN;
    protected String name;

    @Column(name = "publish_year")
    protected int publishYear;

    @Column(name = "num_of_pages")
    protected int numOfPages;

    protected double price;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    protected Set<Reviews> reviews = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    protected Publisher publisher;

    @ElementCollection
    @CollectionTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "ISBN"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"ISBN", "author"})
    )
    @Column(name = "author")
    @ToString.Exclude
    protected Set<String> authors = new LinkedHashSet<>();
}