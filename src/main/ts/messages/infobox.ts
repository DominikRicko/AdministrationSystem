export default function CreateInfo(message : string){

	const closeButton = document.createElement('a');
	closeButton.classList.add("buttonClose");
	closeButton.href = "#";
	closeButton.innerText = 'x';

	closeButton.onclick = ((ev: MouseEvent) => {
		ev.preventDefault();

		//@ts-ignore
		const box = ev.currentTarget.messageBox as HTMLDivElement
		box.remove();
	})

	const messageBox = document.createElement('div');
	messageBox.classList.add("k-messagebox", "k-messagebox-info")
	messageBox.innerText = message;

	//@ts-ignore
	closeButton.messageBox = messageBox;

	$(closeButton).appendTo(messageBox);
	$(messageBox).insertAfter("#header");

}