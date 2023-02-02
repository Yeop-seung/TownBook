import { useState, useEffect } from 'react';

// const DUMMY_DATA = [
//     {
//       id: 'm1',
//       title: 'This is a first meetup',
//       image:
//         'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Stadtbild_M%C3%BCnchen.jpg/2560px-Stadtbild_M%C3%BCnchen.jpg',
//       address: 'Meetupstreet 5, 12345 Meetup City',
//       description:
//         'This is a first, amazing meetup which you definitely should not miss. It will be a lot of fun!',
//     },
//     {
//       id: 'm2',
//       title: 'This is a second meetup',
//       image:
//         'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Stadtbild_M%C3%BCnchen.jpg/2560px-Stadtbild_M%C3%BCnchen.jpg',
//       address: 'Meetupstreet 5, 12345 Meetup City',
//       description:
//         'This is a first, amazing meetup which you definitely should not miss. It will be a lot of fun!',
//     },
//   ];
//jsx에서는 jsx 배열로 렌더링가능 {[]}
//key={}해야 경고사라짐
//이 부분 조금 어렵다 화살표함수
function AllMeetupsPage() {
    const [isLoading, setIsLoading] = useState(true);
    const [loadedMeetups, setLoadedMeetups] = useState([]);

    //화면에 표시되지 않는 부수효과들을 정의할때도 사용됨
    useEffect(() => {
      setIsLoading(true); //여기선뭐 안해도됨 useeffect가 실행될때 true로 초기화
      fetch(
        'http://192.168.140.1/servo1/90',
        {}
      ).then(response => {
        return response.json();
      }).then(data => {
        const meetups = [];

        for (const key in data) {
          const meetup = {
          id: key,
          ...data[key]
        };
          meetups.push(meetup);
        };



        setIsLoading(false);
        setLoadedMeetups(meetups);
      });
  
    }, []);

    
    if (isLoading) {
      <section>
        <p>Loading...</p>
      </section>
    }


    return <div>
        <h1>All AllMeetups Page</h1>
        {/* {[<li>Item1</li>,<li>Item2</li>]} */}
        {/* 이것들은 MeetupList.js 에서 만들어서 주기로 */}
        {/* {DUMMY_DATA.map((meetup) => {
            return <li key={meetup.id}>{meetup.title}</li>
        })} */}
        <button onClick={loadedMeetups

        }>
          신호
        </button>
     
        </div>
        
}
export default AllMeetupsPage;