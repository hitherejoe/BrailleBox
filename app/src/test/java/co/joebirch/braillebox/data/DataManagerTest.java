package co.joebirch.braillebox.data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Callable;

import co.joebirch.braillebox.data.model.NewsResponse;
import co.joebirch.braillebox.data.remote.BrailleBoxService;
import co.joebirch.braillebox.util.BrailleMapper;
import co.joebirch.braillebox.util.TestDataFactory;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for DataManager methods related to retrieving/updating content (articles, videos, etc.)
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock BrailleBoxService mockBrailleBoxService;
    @Mock BrailleMapper mockBrailleMapper;
    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager(mockBrailleBoxService, mockBrailleMapper);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                new Function<Callable<Scheduler>, Scheduler>() {
                    @Override
                    public Scheduler apply(Callable<Scheduler> schedulerCallable) throws Exception {
                        return Schedulers.trampoline();
                    }
                });
    }

    @Test
    public void getArticlesRetrievesArticlesFromBbcSource() {
        NewsResponse newsResponse = TestDataFactory.makeNewsResponse();
        stubBrailleBoxServiceGetArticle(Observable.just(newsResponse));

        dataManager.getArticle(TestDataFactory.randomInt());

        verify(mockBrailleBoxService).getArticles(Source.BBC_NEWS.getId(),
                SortBy.LATEST.getLabel());
    }

    @Test
    public void getArticlesDoesntEmitResultWhenResponseHasError() {
        NewsResponse newsResponse = TestDataFactory.makeNewsResponse();
        stubBrailleBoxServiceGetArticle(Observable.just(newsResponse));

        dataManager.getArticle(TestDataFactory.randomInt());

        verify(mockBrailleMapper, never()).mapFromString(anyString());
    }

    @Test
    public void getArticlesCompletesAndEmitsArticle() {
        NewsResponse newsResponse = TestDataFactory.makeNewsResponse();
        stubBrailleBoxServiceGetArticle(Observable.just(newsResponse));

        TestObserver<String> testSubscriber = new TestObserver<>();
        dataManager.getArticle(TestDataFactory.randomInt()).subscribe(testSubscriber);
        testSubscriber.assertComplete();
        testSubscriber.assertValue(newsResponse.articles.get(0).description);
    }

    /****** Helper methods *******/

    private void stubBrailleBoxServiceGetArticle(Observable<NewsResponse> observable) {
        when(mockBrailleBoxService.getArticles(anyString(), anyString()))
                .thenReturn(observable);
    }

}