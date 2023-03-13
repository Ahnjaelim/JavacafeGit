
async function getList(rcate){
	const result = await axios.get(`/reciperest/cate/${rcate}`)
	return result.data
}

async function getOne(rno){
	const response = await axios.get(`/reciperest/${rno}`)
	return response.data
}