package com.imform.demo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Feed 클래스")
class FeedTest {

    @Test
    @DisplayName("Feed 실수값")
    void creation() {
        Feed feed = new Feed(1L, "imform", "www.imform.co.kr");

        assertThat(feed.getId()).isEqualTo(1L);
        assertThat(feed.getTitle()).isEqualTo("imform");
        assertThat(feed.getUrl()).isEqualTo("www.imform.co.kr");
    }
}
