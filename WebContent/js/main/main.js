new Swiper('.movie-chart .swiper', {
  // direction: 'horizontal';  기본값이므로 생략 가능
  slidesPerView: 5, // 한번에 보여줄 슬라이드 개수
  spaceBetween: 20, // 슬라이드 사이 여백
  centeredSlides: false, // 1번 슬라이드가 가운데 보이기
  loop: true,
  autoplay: true,
  navigation: {
    prevEl: '.movie-chart .swiper-prev',
    nextEl: '.movie-chart .swiper-next'
  }
});