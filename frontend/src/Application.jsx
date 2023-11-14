import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from 'react-router-dom';
import {
  ErrorPage,
  Home,
} from 'pages/';
import { Layout } from 'components/layout';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faCircleExclamation } from '@fortawesome/free-solid-svg-icons';
library.add(faCircleExclamation);

const router = createBrowserRouter(createRoutesFromElements(
  <Route element={<Layout />} errorElement={<ErrorPage />}>
    <Route path={'/'}>
      <Route index element={<Home />} />
    </Route>
    <Route path={'/profile'}>
      <Route index />
    </Route>
  </Route>,
));

const Application = () => {
  return (
    <RouterProvider router={router} />
  );
};

export default Application;
