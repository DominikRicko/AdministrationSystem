export default function CreateError(message : string){

	$(`<div class="k-messagebox k-messagebox-error">${message}</div>`).insertAfter("#header")

}