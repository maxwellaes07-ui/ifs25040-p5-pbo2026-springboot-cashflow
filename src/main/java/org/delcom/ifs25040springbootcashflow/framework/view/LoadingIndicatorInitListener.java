package org.delcom.ifs25040springbootcashflow.framework.view;

import com.vaadin.flow.component.page.LoadingIndicatorConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

/**
 * Konfigurasi global untuk dokumen awal dan indikator pemuatan Vaadin.
 *
 * <p>Dokumen awal hanya dilengkapi metadata aksesibilitas. Resource CSS dan
 * bootstrap JavaScript dibiarkan mengikuti mekanisme standar Vaadin agar
 * tidak mengubah urutan pemuatan yang dibutuhkan saat render pertama.
 */
@Component
public class LoadingIndicatorInitListener implements VaadinServiceInitListener {

    static final String DEFAULT_TITLE = "Cash Flow";
    static final String DEFAULT_LANG = "id";

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.addIndexHtmlRequestListener(response -> prepareDocument(response.getDocument()));
        event.getSource().addUIInitListener(uiEvent -> {
            LoadingIndicatorConfiguration conf = uiEvent.getUI().getLoadingIndicatorConfiguration();
            conf.setApplyDefaultTheme(true);
            conf.setFirstDelay(150);
            conf.setSecondDelay(600);
            conf.setThirdDelay(1500);
        });
    }

    /** Melengkapi HTML awal tanpa menunda stylesheet atau menyisipkan skrip tambahan. */
    static void prepareDocument(Document document) {
        Element html = document.selectFirst("html");
        if (html != null && html.attr("lang").isBlank()) {
            html.attr("lang", DEFAULT_LANG);
        }

        Element head = document.head();
        Element title = head.selectFirst("title");
        if (title == null) {
            head.appendElement("title").text(DEFAULT_TITLE);
        } else if (title.text().isBlank()) {
            title.text(DEFAULT_TITLE);
        }
    }
}
