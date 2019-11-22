package FilmsProject.BusinessLayer;

import FilmsProject.Interfaces.FilmAccessService;
import FilmsProject.Interfaces.FilmService;
import FilmsProject.Model.Film;
import FilmsProject.Model.Review;
import FilmsProject.Model.SearchInRangeProperty;
import FilmsProject.Model.SearchProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmOperations implements FilmService {

    @Autowired
    @Qualifier("filmAccessJPA")
    private FilmAccessService filmAccessService;

    @Override
    public List<Film> searchFilmsByProperty(SearchProperty property, String value) {
        List<Film> foundFilms = new ArrayList<>();
        switch (property) {
            case BY_IDENTIFIER:
                foundFilms.add(filmAccessService.getFilmByIdentifier(value));
                break;
            case BY_TITLE:
                foundFilms.addAll(filmAccessService.getFilmsByTitle(value));
                break;
            case BY_RELEASE_DATE:
                foundFilms.addAll(filmAccessService.getFilmsByReleaseDate(LocalDate.parse(value)));
                break;
        }
        return foundFilms;
    }

    @Override
    public List<Film> searchFilmsInRange(SearchInRangeProperty property, String from, String to) {
        List<Film> foundFilms = new ArrayList<>();
        switch(property) {
            case BY_RATING:
                filmAccessService.getFilmsInRange(Double.parseDouble(from), Double.parseDouble(to));
            case BY_YEAR:
                LocalDate dateFrom = LocalDate.of(Integer.parseInt(from),1,1);
                LocalDate dateTo = LocalDate.of(Integer.parseInt(from),12,31);
                filmAccessService.getFilmsInRange(dateFrom,dateTo);
        }
        return foundFilms;
    }

    @Override
    public List<Review> getReviews(String filmIdentifier) {
        return filmAccessService.getFilmReviews(filmIdentifier);
    }

    @Override
    public Object[] getFilmDetails(String filmIdentifier) {
        List<Object> filmInfoAndReviews = new ArrayList<>();

        filmInfoAndReviews.add(searchFilmsByProperty(SearchProperty.BY_IDENTIFIER,filmIdentifier));
        filmInfoAndReviews.add(getReviews(filmIdentifier));

        return filmInfoAndReviews.toArray();
    }




}
