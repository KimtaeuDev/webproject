// 작성 시간 변환
const getCreated = (time) => {
	
	let result = "방금 전";
	
	let regtime = getTimeArr(new Date(time));
	let nowtime = getTimeArr(new Date());
	let caltime = ['년 전', '개월 전', '일 전', '시간 전', '분 전'];
	
	for(let i = 0; i < 5; i++){
		
		if(regtime[i] < nowtime[i]){
			
			result = (nowtime[i] - regtime[i]) + caltime[i];
			break;
			
		} 
	}

	return result;
};

// 시간 배열 얻기
const getTimeArr = (time) => {
	
	let timeArr = [
		time.getFullYear(),
		time.getMonth() + 1,
		time.getDate(),
		time.getHours(),
		time.getMinutes()];
	
	return timeArr;
};