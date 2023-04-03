package org.example.oneToMany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "many_to_one_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "comment")
    @Column(nullable = false)
    private Set<Post> posts;

    public Comment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        return getAuthorName() != null ? getAuthorName().equals(comment.getAuthorName()) : comment.getAuthorName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAuthorName() != null ? getAuthorName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
               "id=" + id +
               ", authorName='" + authorName + '\'' +
               '}';
    }
}
