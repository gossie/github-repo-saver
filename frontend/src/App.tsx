import { BrowserRouter, Routes, Route } from 'react-router-dom';
import SearchPage from './SearchPage';

function App() {

    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<SearchPage />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
