import Swiper, { Navigation, Pagination, A11y, Keyboard } from "swiper";

const element = document.querySelector(".cmp-vartical-teaser__swiper");

new Swiper(element, {
  modules: [Navigation, Pagination, A11y, Keyboard],
  slidesPerView: 1,
  spaceBetween: 30,
  speed: 800,
  a11y: true,
  breakpoints: {
    576: {
      slidesPerView: 2,
    },
    992: {
      slidesPerView: 3,
    },
    1200: {
      slidesPerView: 4,
    },
  },
  // configure Swiper to use modules
  pagination: {
    el: ".cmp-vartical-teaser__swiper__pagination",
    type: "fraction",
  },

  // Navigation arrows
  navigation: {
    nextEl: ".cmp-vartical-teaser__swiper__button-next",
    prevEl: ".cmp-vartical-teaser__swiper__button-prev",
  },
  keyboard: {
    enabled: true,
    onlyInViewport: false,
  },
});
