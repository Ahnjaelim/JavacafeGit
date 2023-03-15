
async function getList(rcate){
	const result = await axios.get(`/reciperest/cate/${rcate}`)
	return result.data
}

async function getOne(rno){
	const response = await axios.get(`/reciperest/${rno}`)
	return response.data
}

async function addShop(shopObj){
	const response = await axios.post(`/shoprest/`, shopObj)
	return response.data
}

async function todayOrder(today){
	const response = await axios.get(`/shoprest/today/${today}`)
	return response.data
}

async function addShopState(shopStateObj){
	const response = await axios.post(`/shoprest/state/`, shopStateObj)
	return response.data
}
