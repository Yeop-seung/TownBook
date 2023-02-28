function Item(props) {
    return <li>
        <div>
            <img src={props.image} alt={props.title} />
        </div>
        <div>
            <h3>{props.title}</h3>
            <p>{props.content}</p>
        </div>
        <div>
            <button></button>
        </div>
    </li>
}

export default Item