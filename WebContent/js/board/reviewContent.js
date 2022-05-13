const reply = document.fr.reply;
const lengthCalc = document.querySelector('.lengthCalc');

function calcInputLength() {
  lengthCalc.textContent = 1000-reply.value.length;
  if(reply.value.length>=1000) {
    alert("1000자 까지만 작성 가능합니다.");
  }
}