package br.edu.ifsp.graphql.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.edu.ifsp.graphql.model.Character;
import br.edu.ifsp.graphql.model.Droid;
import br.edu.ifsp.graphql.model.Episode;
import br.edu.ifsp.graphql.model.Human;
import br.edu.ifsp.graphql.model.Review;
import br.edu.ifsp.graphql.model.ReviewInput;
import br.edu.ifsp.graphql.model.Starship;

@Controller
public class StarWarController {

    // Meu 'banco de dados' provisorio pra query de add character funcionar
    private List<Character> tabelaCharacters = new ArrayList<>(List.of(
        new Human("1001", "Luke Skywalker", new ArrayList<>(), new ArrayList<>(), 1.72),
        new Droid("2001", "R2-D2", new ArrayList<>(), new ArrayList<>(), "Astromech"),
        new Human("1002", "Darth Vader", new ArrayList<>(), new ArrayList<>(), 2.02)
    ));

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * * type Query {
     * hero(episode: Episode): Character
     * }
     */
    @QueryMapping
    public Character hero(@Argument Episode episode) {
        return new Human(
                "1001",
                "Luke Skaywalker",
                List.of(episode != null ? episode : Episode.NEWHOPE),
                List.of(),
                1.75);
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * * type Query {
     * droid(id: ID!): Droid
     * }
     */
    @QueryMapping
    public Droid droid(@Argument String id) {
        return new Droid(
                id,
                "R2-D2",
                List.of(Episode.NEWHOPE, Episode.EMPIRE, Episode.JEDI),
                List.of(),
                "Astromech");
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * * type Query {
     * search(text: String!): [SearchResult!]!
     * }
     */
    @QueryMapping
    public List<Object> search(@Argument String text) {
        return List.of(
                new Droid("2001", "R2-D2", List.of(), List.of(), "Astromech"),
                new Human("1001", "Luke Skywalker", List.of(), List.of(), 1.72f),
                new Starship(3000, "Millenium Falcon", 1000));
    }

    @QueryMapping
    public List<Human> humans() {
        List<Human> lista = new ArrayList<>();
        for (Character c : tabelaCharacters) {
            if (c instanceof Human) lista.add((Human) c);
        }
        return lista;
    }

    @QueryMapping
    public List<Starship> starships() {
        return List.of(
            new Starship(3000, "Millenium Falcon", 34.37),
            new Starship(3001, "X-Wing", 12.5)
        );
    }

    @QueryMapping
    public Character character(@Argument String id) {
        return tabelaCharacters.stream()
            .filter(c -> c.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    /*
     * Mapeado no resources/graphql/scheme.graphqls
     * * type Mutation {
     * createReview(episode: Episode!, review: ReviewInput!): Review
     * }
     */

    // =========================
    //         MUTAÇÕES
    // =========================
    @MutationMapping
    public Review createReview(@Argument Episode episode, @Argument ReviewInput review) {
        return new Review(review.getStars(), review.getCommentary());
    }

    @MutationMapping
    public Human createHuman(@Argument String id, @Argument String name, @Argument Double height) {
        Human novo = new Human(id, name, new ArrayList<>(), new ArrayList<>(), height);
        tabelaCharacters.add(novo);
        return novo;
    }

    @MutationMapping
    public Droid createDroid(@Argument String id, @Argument String name, @Argument String primaryFunction) {
        Droid novo = new Droid(id, name, new ArrayList<>(), new ArrayList<>(), primaryFunction);
        tabelaCharacters.add(novo);
        return novo;
    }

    @MutationMapping
    public Starship createStarship(@Argument String id, @Argument String name, @Argument Double length) {
        return new Starship(Integer.parseInt(id), name, length);
    }

    @MutationMapping
    public Character addFriend(@Argument String characterId, @Argument String friendId) {
        Character principal = null;
        Character amigo = null;

        for (Character character : tabelaCharacters) {
            if (character.getId().equals(characterId)) principal = character;
            if (character.getId().equals(friendId)) amigo = character;
        }

        if (principal != null && amigo != null) {
            principal.getFriends().add(amigo);
        }
        return principal;
    }

}