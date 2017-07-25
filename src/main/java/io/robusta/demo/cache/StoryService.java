package io.robusta.demo.cache;

import java.util.Optional;

public class StoryService {

    static PeopleStory story1 = new PeopleStory(1, "Robusta Code works on Parser-Combinator JS");
    static PeopleStory story2 = new PeopleStory(
            2,
            "Robusta Code worked on Robusta Rest Adapter (www.rra.io)");
    static PeopleStory story3 = new PeopleStory(
            3,
            "Robusta Code goes Serverless");

    public Optional<PeopleStory> getStory(int id) {

        switch (id) {
            case 1:
                return Optional.of(story1);

            case 2:
                return Optional.of(story2);
            case 3:
                return Optional.of(story3);
            default:
                return Optional.empty();
        }

    }
}
