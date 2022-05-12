// 유튜브 iframe API
var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

function onYouTubeIframeAPIReady() {
  new YT.Player('player', {
    videoId: 'rCToVN37IDg',
    playerVars: {
      autoplay: true,
      loop: true,
      playlist: 'rCToVN37IDg',
    },
    events: {
      'onReady': onPlayerReady
    }
  });
}
function onPlayerReady(event) {
  event.target.mute();
  event.target.setPlaybackQuality("hd1080");
  event.target.playVideo();
}
