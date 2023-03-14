
async function getList(rcate){
	const result = await axios.get(`/reciperest/cate/${rcate}`)
	return result.data
}

async function getOne(rno){
	const response = await axios.get(`/reciperest/${rno}`)
	return response.data
}

async function addKiosk(kioskObj){
	const response = await axios.post(`/kioskrest/`, kioskObj)
	return response.data
}

async function addKioskState(kioskStateObj){
	const response = await axios.post(`/kioskrest/state/`, kioskStateObj)
	return response.data
}
