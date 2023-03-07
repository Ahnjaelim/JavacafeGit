async function get1(sno){
	const result = await axios.get(`/`)
	return result.data
	console.log(data)

}


async function getList({sno, page, size, goLast}){
	const result = await axios.get(`/sales/read?sno=2`,{params:{page,size}})
	return result.data
}