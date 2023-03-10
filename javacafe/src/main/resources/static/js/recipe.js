
async function getList(rno){
	const result = await axios.get(`/recipestock/list/${rno}`)
	return result.data
}

async function addStock(stockObj){
	const response = await axios.post(`/recipestock/`, stockObj)
	return response.data
}